const container = document.getElementById("jsoneditor")
const options = {}
const editor = new JSONEditor(container, options)

const initialJson = {
  "Array": [1, 2, 3],
  "Boolean": true,
  "Null": null,
  "Number": 123,
  "Object": {"a": "b", "c": "d"},
  "String": "Hello World"
}
editor.set(initialJson)

const updatedJson = editor.get()