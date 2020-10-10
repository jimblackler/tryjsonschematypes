function showError(err) {
  document.getElementById('errorMessage').innerText = err.message;
  errorDialog.showModal();
}

function setTabSelection() {
  [...document.getElementsByClassName('mdl-layout__tab')].forEach(element => {
    element.classList.remove('is-active');
    if (element.href === location.href) {
      element.classList.add('is-active');
    }
  });
  [...document.getElementsByClassName('tab-content')].forEach(element => {
    element.style.display = 'none';
  });
  [...document.getElementsByClassName(location.hash.substring(1))].forEach(
      element => {
        element.style.display = null;
      });
}

if (!location.hash) {
  location.hash = '#validate';
}

setTabSelection();
window.addEventListener('hashchange', setTabSelection);

const jsonDialog = document.getElementById('jsonDialog');
if (!jsonDialog.showModal) {
  dialogPolyfill.registerDialog(jsonDialog);
}

jsonDialog.querySelector('.close').addEventListener('click', function() {
  jsonDialog.close();
});

const errorDialog = document.getElementById('errorDialog');
if (!errorDialog.showModal) {
  dialogPolyfill.registerDialog(errorDialog);
}

errorDialog.querySelector('.close').addEventListener('click', function() {
  errorDialog.close();
});

const schemaEditor = ace.edit(document.getElementById('schemaEditor'), {
  mode: 'ace/mode/json',
  theme: 'ace/theme/crimson_editor',
  fontSize: '14px'
});
let findSchemaTimer = null;
schemaEditor.on('change', () => {
  if (findSchemaTimer != null) {
    clearTimeout(findSchemaTimer);
  }
  findSchemaTimer = setTimeout(() => {
    let schemaData;
    try {
      schemaData = JSON.parse(schemaEditor.getValue());
    } catch (e) {
      return;
    }
    if ('$schema' in schemaData) {
      const schema = schemaData['$schema'];
      [...document.querySelectorAll('ul[for=metaschema] > li')].forEach(el => {
        if (el.getAttribute('data-val') === schema) {
          el.click();
        }
      });
    }
  }, 200);
});

const metaschema = document.getElementById('metaschema');
metaschema.addEventListener('change', evt => {
  let schemaData;
  try {
    schemaData = JSON.parse(`[${schemaEditor.getValue()}]`);
  } catch (e) {
    return;
  }
  if (schemaData.length === 0) {
    schemaData = {};
  } else if (schemaData.length === 1) {
    schemaData = schemaData[0];
  } else {
    return;
  }

  if (schemaData['$schema'] === metaschema.value) {
    return;
  }
  schemaData['$schema'] = metaschema.value;
  schemaEditor.setValue(JSON.stringify(schemaData, null, '\t'), -1);
});

const jsonResults = ace.edit(document.getElementById('jsonResults'), {
  mode: 'ace/mode/json',
  theme: 'ace/theme/crimson_editor',
  fontSize: '14px',
  showLineNumbers: false,
  readOnly: true
});

const demoSelector = document.getElementById('demoSelector');
const demoProgress = document.getElementById('demoProgress');
demoProgress.style.visibility = 'visible';

const documentEditor = ace.edit(document.getElementById('documentEditor'), {
  mode: 'ace/mode/json',
  theme: 'ace/theme/crimson_editor',
  fontSize: '14px'
});

const javaEditor = ace.edit(document.getElementById('javaEditor'), {
  mode: 'ace/mode/java',
  theme: 'ace/theme/crimson_editor',
  fontSize: '14px',
  readOnly: true
});

const typeScriptEditor = ace.edit(document.getElementById('typeScriptEditor'), {
  mode: 'ace/mode/typescript',
  theme: 'ace/theme/crimson_editor',
  fontSize: '14px',
  readOnly: true
});

window.onload = () => {
  fetch('allDemos.json')
      .then(
          response =>
              response.ok ? response.json() : response.json().then(json => {
                throw new Error(json.message);
              }))
      .then(json => {
        const demoList = document.getElementById('demoList');
        json.forEach(element => {
          const li = document.createElement('li');
          li.classList.add('mdl-menu__item');
          li.setAttribute('data-val', element);
          li.appendChild(document.createTextNode(element));
          demoList.appendChild(li);
        });
        getmdlSelect.init('#demoSelector');
        document.getElementById('demo').addEventListener('change', evt => {
          const demoFetch = new URL('demoData', document.location);
          schemaEditor.setValue('');
          documentEditor.setValue('');
          javaEditor.setValue('');
          typeScriptEditor.setValue('');
          demoProgress.style.visibility = 'visible';
          const schemaFetch = new URL('demoSchema', document.location);
          schemaFetch.searchParams.append('demo', evt.target.value);
          fetch(schemaFetch)
              .then(
                  response => response.ok ? response.json() :
                                            response.json().then(json => {
                                              throw new Error(json.message);
                                            }))
              .then(json => {
                schemaEditor.setValue(JSON.stringify(json, null, '\t'), -1);
                if (location.hash !== '#generate') {
                  demoFetch.searchParams.append('demo', evt.target.value);
                  return fetch(demoFetch).then(
                      response => response.ok ? response.json() :
                          response.json().then(json => {
                            throw new Error(json.message);
                          }))
                      .then(json => {
                        documentEditor.setValue(JSON.stringify(json, null, '\t'), -1);
                      });
                }
              })
              .catch(showError)
              .finally(() => demoProgress.style.visibility = 'hidden');
        });
        demoSelector.style.visibility = 'visible';
      })
      .catch(showError)
      .finally(() => demoProgress.style.visibility = 'hidden');
};

document.getElementById('actionButton').addEventListener('click', evt => {
  const actionProgress = document.getElementById('actionProgress');
  actionProgress.style.visibility = 'visible';

  const params = new URLSearchParams();
  params.append('schema', schemaEditor.getValue());

  switch (location.hash) {
    case '#validate':
      params.append('document', documentEditor.getValue());
      fetch('validate', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: params.toString()
      })
          .then(
              response =>
                  response.ok ? response.json() : response.json().then(json => {
                    throw new Error(json.message);
                  }))
          .then(json => {
            document.getElementById('result').innerText = json.result;
            jsonResults.setValue(
                JSON.stringify(json.validation, null, '\t'), -1);
            jsonDialog.showModal();
          })
          .catch(showError)
          .finally(() => actionProgress.style.visibility = 'hidden');
      break;
    case '#generate':
      documentEditor.setValue('');
      fetch('generate', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: params.toString()
      })
          .then(
              response =>
                  response.ok ? response.json() : response.json().then(json => {
                    throw new Error(json.message);
                  }))
          .then(json => {
            documentEditor.setValue(JSON.stringify(json.generated, null, '\t'), -1);
          })
          .catch(showError)
          .finally(() => actionProgress.style.visibility = 'hidden');
      break;
    case '#java':
      params.append('document', documentEditor.getValue());
      fetch('java', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: params.toString()
      })
          .then(
              response =>
                  response.ok ? response.text() : response.json().then(json => {
                    throw new Error(json.message);
                  }))
          .then(text => {
            javaEditor.setValue(text, -1);
          })
          .catch(showError)
          .finally(() => actionProgress.style.visibility = 'hidden');
      break;
    case '#typescript':
      params.append('document', documentEditor.getValue());
      fetch('typescript', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: params.toString()
      })
          .then(
              response =>
                  response.ok ? response.text() : response.json().then(json => {
                    throw new Error(json.message);
                  }))
          .then(text => {
            typeScriptEditor.setValue(text, -1);
          })
          .catch(showError)
          .finally(() => actionProgress.style.visibility = 'hidden');
      break;
  }
});