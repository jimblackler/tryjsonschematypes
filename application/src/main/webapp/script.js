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

document.getElementById('demo').addEventListener(
    'change',
    evt => {fetch(`demoData?demo=${encodeURIComponent(evt.target.value)}`)
                .then(response => response.json())
                .then(json => {
                  schemaEditor.setValue(
                      JSON.stringify(json.schema, null, '\t'));
                  documentEditor.setValue(
                      JSON.stringify(json.document, null, '\t'));
                })});
