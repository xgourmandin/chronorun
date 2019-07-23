<template>
  <v-app dark>
      <v-navigation-drawer
              v-model="drawer"
              fixed
              app
      >
          <v-list dense>
              <v-list-tile
                      v-for="item in items"
                      :key="item.title"
              >
                  <v-list-tile-action>
                      <v-icon>{{ item.icon }}</v-icon>
                  </v-list-tile-action>

                  <v-list-tile-content>
                      <v-list-tile-title>
                          <router-link active-class="router-link-active" class="router-link" exact :to="item.route">{{ item.title }}</router-link>
                      </v-list-tile-title>
                  </v-list-tile-content>
              </v-list-tile>
          </v-list>
      </v-navigation-drawer>
      <v-toolbar color="indigo" dark fixed app>
          <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
          <v-toolbar-title><v-img src="logo.png" width="50" contain /></v-toolbar-title>
      </v-toolbar>
      <v-content>
          <router-view></router-view>
      </v-content>
      <v-footer color="indigo" app>
          <span class="white--text">&copy; 2019</span>
      </v-footer>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data () {
    return {
        drawer: null,
        items: [
            { title: 'Courses', icon: 'flag', route: '/' },
            { title: 'Inscriptions', icon: 'edit', route: 'registration' },
            { title: 'Chronomètre', icon: 'schedule', route: 'chrono' },
            { title: 'Arrivée', icon: 'how_to_reg', route: 'finish' },
            { title: 'Résultats', icon: 'assignment', route:'results' },
        ]
    }
  }
}
</script>

<style>
  .router-link {
    text-decoration: none;
    color: grey;
  }

  .router-link-active {
    text-decoration: none;
    color: white;
  }

</style>
