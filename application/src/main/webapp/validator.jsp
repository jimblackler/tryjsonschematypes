<!DOCTYPE html>
<head>
  <title>Jim Blackler's JSON Schema tools</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/getmdl-select/2.0.1/getmdl-select.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.12/ace.js"></script>
  <script src="https://code.getmdl.io/1.3.0/material.js"></script>
  <script
      src="https://cdnjs.cloudflare.com/ajax/libs/getmdl-select/2.0.1/getmdl-select.min.js"></script>
  <link rel="stylesheet" href="styles.css">
  <script defer src="script.js"></script>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
  <header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
      <span class="mdl-layout-title">Jim Blackler's JSON Schema tools</span>
      <div class="mdl-layout-spacer"></div>
      <nav class="mdl-navigation">
        <a class="mdl-navigation__link" href="">Link</a>
        <a class="mdl-navigation__link" href="">Link</a>
        <a class="mdl-navigation__link" href="">Link</a>
        <a class="mdl-navigation__link" href="">Link</a>
      </nav>
    </div>
    <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
      <a href="#tab-1" class="mdl-layout__tab is-active">Validator</a>
      <a href="#tab-2" class="mdl-layout__tab">Test data generator</a>
      <a href="#tab-3" class="mdl-layout__tab">Java generator</a>
      <a href="#tab-4" class="mdl-layout__tab">Typescript generator</a>
    </div>
  </header>
  <div class="mdl-layout__drawer">
    <span class="mdl-layout-title">Tools</span>
  </div>
  <main class="mdl-layout__content">
    <div class="mdl-grid">
      <div class="mdl-cell mdl-cell--12-col mdl-cell--8-col-tablet mdl-cell--4-col-phone">
        <div class="noncard mdl-shadow--2dp">
          <p>An online application of
            <a href="https://github.com/jimblackler/jsonschematypes/tree/master/library">jsonschemafriend</a>
          a <a href="https://json-schema.org/">JSON Schema</a> Validator library written in Java by Jim Blackler. Paste your own schema
          and document for validation in the editors below, or choose a demo.</p>
          <div
              class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select__fix-height metaschema-textfield">
            <input type="text" value="" class="mdl-textfield__input" id="demo" readonly>
            <input type="hidden" value="" name="demo">
            <i class="mdl-icon-toggle__label material-icons">keyboard_arrow_down</i>
            <label for="demo" class="mdl-textfield__label">Demo</label>
            <ul for="demo" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
              <li class="mdl-menu__item" data-val="http://json-schema.org/draft-03/schema#">
                http://json-schema.org/draft-03/schema#
              </li>
              <li class="mdl-menu__item" data-val="http://json-schema.org/draft-04/schema#">
                http://json-schema.org/draft-04/schema#
              </li>
              <li class="mdl-menu__item" data-val="http://json-schema.org/draft-06/schema#">
                http://json-schema.org/draft-06/schema#
              </li>
              <li class="mdl-menu__item" data-val="http://json-schema.org/draft-07/schema#"
                  data-selected="true">http://json-schema.org/draft-07/schema#
              </li>
              <li class="mdl-menu__item" data-val="https://json-schema.org/draft/2019-09/schema">
                https://json-schema.org/draft/2019-09/schema
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="mdl-cell mdl-cell--6-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
        <div class="noncard compact">
          <h4>Schema</h4>
          <div
              class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select__fix-height metaschema-textfield">
            <input type="text" value="" class="mdl-textfield__input" id="metaschema" readonly>
            <input type="hidden" value="" name="metaschema">
            <i class="mdl-icon-toggle__label material-icons">keyboard_arrow_down</i>
            <label for="metaschema" class="mdl-textfield__label">Metaschema</label>
            <ul for="metaschema" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
              <li class="mdl-menu__item" data-val="http://json-schema.org/draft-03/schema#">
                http://json-schema.org/draft-03/schema#
              </li>
              <li class="mdl-menu__item" data-val="http://json-schema.org/draft-04/schema#">
                http://json-schema.org/draft-04/schema#
              </li>
              <li class="mdl-menu__item" data-val="http://json-schema.org/draft-06/schema#">
                http://json-schema.org/draft-06/schema#
              </li>
              <li class="mdl-menu__item" data-val="http://json-schema.org/draft-07/schema#">
                http://json-schema.org/draft-07/schema#
              </li>
              <li class="mdl-menu__item" data-val="https://json-schema.org/draft/2019-09/schema"
                  data-selected="true">
                https://json-schema.org/draft/2019-09/schema
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="mdl-cell mdl-cell--6-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
        <div class="noncard compact">
          <h4>Document</h4>
          <button
              class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Validate
          </button>
        </div>
      </div>
    </div>
    <div class="mdl-grid">
      <div class="mdl-cell mdl-cell--6-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
        <div id="jsoneditor0"></div>
      </div>

      <div class="mdl-cell mdl-cell--6-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
        <div id="jsoneditor1"></div>
      </div>
      <div class="mdl-cell mdl-cell--12-col">
        <section class="mdl-layout__tab-panel is-active" id="tab-1">
          <div class="page-content">1</div>
        </section>
        <section class="mdl-layout__tab-panel" id="tab-2">
          <div class="page-content">2</div>
        </section>
        <section class="mdl-layout__tab-panel" id="tab-3">
          <div class="page-content">3</div>
        </section>
        <section class="mdl-layout__tab-panel" id="tab-4">
          <div class="page-content">4</div>
        </section>
      </div>
    </div>
  </main>
</div>
</body>