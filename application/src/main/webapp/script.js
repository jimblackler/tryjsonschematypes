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

let schemaEditor = initJsonEditor(document.getElementById('schemaEditor'));
let documentEditor = initJsonEditor(document.getElementById('documentEditor'));

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
  fetch(demoFetch).then(response => response.json()).then(json => {
    schemaEditor.setValue(JSON.stringify(json.schema, null, '\t'));
    documentEditor.setValue(JSON.stringify(json.document, null, '\t'));
  })
});

document.getElementById('validate').addEventListener('click', evt => {
  const validateFetch = new URL('validate', document.location);
  validateFetch.searchParams.append('schema', schemaEditor.getValue());
  validateFetch.searchParams.append('document', documentEditor.getValue());
  fetch(validateFetch).then(response => response.json()).then(json => {
    alert(JSON.stringify(json, null, 2));
  })
});