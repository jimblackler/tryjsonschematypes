<!DOCTYPE html>
<header>
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
  <link rel="stylesheet" href="styles.css">
  <script defer src="https://code.getmdl.io/1.3.0/material.js"></script>
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
  </main>
</div>
</body>