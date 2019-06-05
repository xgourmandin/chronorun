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
                          <router-link :to="item.route">{{ item.title }}</router-link>
                      </v-list-tile-title>
                  </v-list-tile-content>
              </v-list-tile>
          </v-list>
      </v-navigation-drawer>
      <v-toolbar color="indigo" dark fixed app>
          <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
          <v-toolbar-title>Mazères Course Pédestre</v-toolbar-title>
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
            { title: 'Courses', icon: 'dashboard', route: '/' },
            { title: 'Inscriptions', icon: 'question_answer', route: 'registration' },
            { title: 'Chronomètre', icon: 'question_answer', route: 'chrono' },
            { title: 'Résultats', icon: 'question_answer', route:'results' },
        ]
    }
  }
}
</script>
