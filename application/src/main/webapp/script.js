/**
 * Initialize a JSON editor
 * @param container {HTMLElement}
 */
function initJsonEditor(container) {
  const editor = ace.edit(container,
      {
        mode: "ace/mode/json",
        theme: "ace/theme/crimson_editor",
        fontSize: "14px"
      });

  editor.setValue(JSON.stringify({
    "Array": [1, 2, 3],
    "Boolean": true,
    "Null": null,
    "Number": 123,
    "Object": {"a": "b", "c": "d"},
    "String": "Hello World"
  }, null, "\t"));

}

initJsonEditor(document.getElementById("jsoneditor0"));
initJsonEditor(document.getElementById("jsoneditor1"));
