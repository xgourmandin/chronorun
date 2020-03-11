<template>
  <v-app dark>
      <v-navigation-drawer
              v-model="drawer"
              app
              dark
              >
          <v-list dense>
              <v-list-item
                      v-for="item in items"
                      :key="item.title"
              >
                  <v-list-item-icon>
                      <v-icon>{{ item.icon }}</v-icon>
                  </v-list-item-icon>

                  <v-list-item-content>
                      <v-list-item-title>
                          <router-link active-class="router-link-active" class="router-link" exact :to="item.route">{{ item.title }}</router-link>
                      </v-list-item-title>
                  </v-list-item-content>
              </v-list-item>
          </v-list>
      </v-navigation-drawer>
      <v-app-bar color="indigo" dark clipped-right app>
          <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
          <v-toolbar-title><v-img src="logo.png" width="50" contain /></v-toolbar-title>
      </v-app-bar>
      <v-content>
          <router-view></router-view>
      </v-content>
      <v-footer color="indigo" app>
          <span class="white--text">&copy; 2020</span>
        <v-spacer></v-spacer>
        <realtime-server></realtime-server>
      </v-footer>
  </v-app>
</template>

<script>

import RealtimeServer from "./components/realtime-server";
export default {
  name: 'App',
  components: {RealtimeServer},
  data () {
    return {
        drawer: null,
        items: [
            { title: 'Courses', icon: 'mdi-flag', route: '/' },
            { title: 'Inscriptions', icon: 'mdi-pencil', route: 'registration' },
            { title: 'Chronomètre', icon: 'mdi-alarm', route: 'chrono' },
            { title: 'Arrivée', icon: 'mdi-flag-checkered', route: 'finish' },
            { title: 'Résultats', icon: 'mdi-clipboard-list', route:'results' },
            { title: 'Affichage', icon: 'mdi-projector-screen', route:'display' },
        ]
    }
  },
}
</script>

<style>
  .router-link {
    text-decoration: none !important;
    color: grey !important;
  }

  .router-link-active {
    text-decoration: none !important;
    color: white !important;
  }

</style>
