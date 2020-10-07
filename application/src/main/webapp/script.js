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



/**
 * Initialize an Ace editor
 * @param container {HTMLElement}
 * @return The Ace editor.
 */
function initJsonEditor(container) {
  return ace.edit(container, {
    mode: 'ace/mode/json',
    theme: 'ace/theme/crimson_editor',
    fontSize: '14px'
  });
}

const schemaEditor = initJsonEditor(document.getElementById('schemaEditor'));
const documentEditor =
    initJsonEditor(document.getElementById('documentEditor'));
const jsonResults = initJsonEditor(document.getElementById('jsonResults'));
jsonResults.setReadOnly(true);

fetch('allDemos.json').then(response => response.json()).then(json => {
  const demoList = document.getElementById('demoList');
  json.forEach(element => {
    const li = document.createElement('li');
    li.classList.add('mdl-menu__item');
    li.setAttribute('data-val', element);
    li.appendChild(document.createTextNode(element));
    demoList.appendChild(li);
  });
});


document.getElementById('demo').addEventListener('change', evt => {
  const demoFetch = new URL('demoData', document.location);
  demoFetch.searchParams.append('demo', evt.target.value);
  const demoProgress = document.getElementById('demoProgress');
  demoProgress.style.visibility = 'visible';
  fetch(demoFetch)
      .then(
          response => response.json().then(
              response.ok ?
                  json => {
                    schemaEditor.setValue(
                        JSON.stringify(json.schema, null, '\t'), -1);
                    documentEditor.setValue(
                        JSON.stringify(json.document, null, '\t'), -1);
                  } :
                  json => {
                    throw new Error(json.message);
                  }))
      .catch(err => {
        document.getElementById('errorMessage').innerText = err.message;
        errorDialog.showModal();
      })
      .finally(() => demoProgress.style.visibility = 'hidden');
});

document.getElementById('validate').addEventListener('click', evt => {
  const params = new URLSearchParams();
  params.append('schema', schemaEditor.getValue());
  params.append('document', documentEditor.getValue());
  const validateProgress = document.getElementById('validateProgress');
  validateProgress.style.visibility = 'visible';
  fetch('validate', {
    method: 'POST',
    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    body: params.toString()
  })
      .then(
          response => response.json().then(
              response.ok ?
                  json => {
                    document.getElementById('result').innerText = json.result;
                    jsonResults.setValue(
                        JSON.stringify(json.validation, null, '\t'), -1);
                    jsonDialog.showModal();
                  } :
                  json => {
                    throw new Error(json.message);
                  }))
      .catch(err => {
        document.getElementById('errorMessage').innerText = err.message;
        errorDialog.showModal();
      })
      .finally(() => validateProgress.style.visibility = 'hidden');
});