<script>
  import {authComputed} from '@state/helpers'
  import NavBarRoutes from './nav-bar-routes'

  export default {
    components: {NavBarRoutes},
    data() {
      return {
        persistentNavRoutes: [
          {
            name: 'parameters',
            title: 'Paramètres',
          },
          {
            name: 'Inscription',
            title: 'Inscription',
          },
          {
            name: 'chrono',
            title: 'Chrono',
          },
          {
            name: 'Resultats',
            title: 'Résultats',
          },
        ],
        loggedInNavRoutes: [],
        loggedOutNavRoutes: [],
      }
    },
    computed: {
      ...authComputed,
    },
  }
</script>

<template>
  <ul :class="$style.container">
    <NavBarRoutes :routes="persistentNavRoutes"/>
    <NavBarRoutes v-if="loggedIn" :routes="loggedInNavRoutes"/>
    <NavBarRoutes v-else :routes="loggedOutNavRoutes"/>
  </ul>
</template>

<style lang="scss" module>
  @import '@design';

  .container {
    width: 100%;
    padding: 0.75em 0;
    margin: 0 0 $size-grid-padding;
    text-align: center;
    list-style-type: none;
    background-color: #35495e;

    > li {
      display: inline-block;
      margin-right: $size-grid-padding;
    }
  }
</style>
