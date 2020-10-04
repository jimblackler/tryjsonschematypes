function initJsonEditor(container) {
  new JSONEditor(container, {
    mode: "code",
    modes: ["tree", "code"]
  }).set({
    "Array": [1, 2, 3],
    "Boolean": true,
    "Null": null,
    "Number": 123,
    "Object": {"a": "b", "c": "d"},
    "String": "Hello World"
  });
}

initJsonEditor(document.getElementById("jsoneditor0"));
initJsonEditor(document.getElementById("jsoneditor1"));
