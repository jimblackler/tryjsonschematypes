function handle500(response) {
  return response.json().then(response.ok ? json => json : json => {
    throw new Error(json.message);
  });
}

function showError(err) {
  document.getElementById('errorMessage').innerText = err.message;
  errorDialog.showModal();
}

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


const schemaEditor = initJsonEditor(document.getElementById('schemaEditor'));
const documentEditor =
    initJsonEditor(document.getElementById('documentEditor'));
const jsonResults = initJsonEditor(document.getElementById('jsonResults'));
jsonResults.setReadOnly(true);

const demoSelector = document.getElementById('demoSelector');
const demoProgress = document.getElementById('demoProgress');
demoProgress.style.visibility = 'visible';

window.onload = () => {
  fetch('allDemos.json')
      .then(handle500)
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
          demoFetch.searchParams.append('demo', evt.target.value);
          demoProgress.style.visibility = 'visible';
          documentEditor.setValue("");
          schemaEditor.setValue("");
          fetch(demoFetch)
              .then(handle500)
              .then(json => {
                documentEditor.setValue(JSON.stringify(json, null, '\t'), -1);
                const schemaFetch = new URL('demoSchema', document.location);
                schemaFetch.searchParams.append('demo', evt.target.value);
                return fetch(schemaFetch);
              })
              .then(handle500)
              .then(json => {
                schemaEditor.setValue(JSON.stringify(json, null, '\t'), -1);
              })
              .catch(showError)
              .finally(() => demoProgress.style.visibility = 'hidden');
        });
        demoSelector.style.visibility = 'visible';
      })
      .catch(showError)
      .finally(() => demoProgress.style.visibility = 'hidden');
};

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
      .then(handle500)
      .then(json => {
        document.getElementById('result').innerText = json.result;
        jsonResults.setValue(JSON.stringify(json.validation, null, '\t'), -1);
        jsonDialog.showModal();
      })
      .catch(showError)
      .finally(() => validateProgress.style.visibility = 'hidden');
});