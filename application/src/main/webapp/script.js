const container = document.getElementById("jsoneditor");
const options = {
  mode: "code",
  modes: ["tree", "view", "code", "text"]
};
const editor = new JSONEditor(container, options);

const initialJson = {
  "Array": [1, 2, 3],
  "Boolean": true,
  "Null": null,
  "Number": 123,
  "Object": {"a": "b", "c": "d"},
  "String": "Hello World"
};
editor.set(initialJson);

const updatedJson = editor.get();