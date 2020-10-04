<!DOCTYPE html>
<header>
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
</header>
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
    <span class="mdl-layout-title">Title</span>
  </div>
  <main class="mdl-layout__content">
    <div class="mdl-grid">
      <div class="mdl-cell mdl-cell--8-col">
        <div class="mdl-card mdl-shadow--2dp full-width">
          <div class="mdl-card__title">
            <h2 class="mdl-card__title-text">Online JSON validator</h2>
          </div>
          <div class="mdl-card__supporting-text">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt
            ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
            ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
            reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
            sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id
            est laborum.
          </div>
          <div class="mdl-card__actions mdl-card--border">
            <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
              Get Started
            </a>
          </div>
        </div>
      </div>
      <div class="mdl-cell mdl-cell--4-col">
        <div class="mdl-card mdl-shadow--2dp full-width">
          <div class="mdl-card__title">
            <h2 class="mdl-card__title-text">Online JSON validator</h2>
          </div>
          <div class="mdl-card__supporting-text">
            Try a demo blah blah
          </div>
          <div class="mdl-card__actions mdl-card--border">
            <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
              Get Started
            </a>
          </div>
        </div>
      </div>
      <div class="mdl-cell mdl-cell--6-col">
        <div
            class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select__fix-height">
          <input type="text" value="" class="mdl-textfield__input" id="sample6" readonly>
          <input type="hidden" value="" name="sample6">
          <i class="mdl-icon-toggle__label material-icons">keyboard_arrow_down</i>
          <label for="sample6" class="mdl-textfield__label">Country</label>
          <ul for="sample6" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
            <li class="mdl-menu__item" data-val="BY" data-selected="true">Belarus</li>
            <li class="mdl-menu__item" data-val="BR">Brazil</li>
            <li class="mdl-menu__item" data-val="ES">Estonia</li>
            <li class="mdl-menu__item" data-val="FI">Finland</li>
            <li class="mdl-menu__item" data-val="FR">France</li>
            <li class="mdl-menu__item" data-val="DE">Germany</li>
            <li class="mdl-menu__item" data-val="PL">Poland</li>
            <li class="mdl-menu__item" data-val="RU">Russia</li>
          </ul>
        </div>
        <div id="jsoneditor0"></div>
      </div>

      <div class="mdl-cell mdl-cell--6-col">
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