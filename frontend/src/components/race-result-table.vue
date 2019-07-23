<script>
  import {mapState} from 'vuex'
  import store from '../store'

  export default {
    filters: {
      capitalize: function (str) {
        return str.charAt(0).toUpperCase() + str.slice(1)
      }
    },
    data: function () {
      return {
        search: '',
        headers: [
          {text: 'Dossard', value: 'contestant.bib'},
          {text: 'Nom', value: 'contestant.name'},
          {text: 'Catégorie', value: 'contestant.category'},
          {text: 'Temps', value: 'raceTime'},
          {text: 'Moyenne/Km', value: 'meanPaceByKm'},
          {text: 'Club', value: 'contestant.club'},
        ],
        pagination: {'sortBy': 'raceTime', 'descending': false, 'rowsPerPage': -1}
      }
    },
    computed: {
      ...mapState( {
        raceResults: (state) => state.results.raceResults,
      }),
    },
    mounted(){
      store.dispatch('loadResults')
    },
    methods: {
      timeFormat: function (millis) {
        let time = new Date(millis)
        return time.getHours() + "H" + time.getMinutes() + "M" + time.getSeconds() + "s"
      },
      durationFormat: function(durationString) {
        return durationString.substring(2)
      }
    }
  }
</script>

<template>
  <v-card>
    <v-card-title>
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="search"
        label="Chercher"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>

    <v-data-table
      :headers="headers"
      :items="raceResults"
      :search="search"
      :pagination.sync="pagination"
    >
      <template v-slot:no-data>
        Pas de résultats disponible
      </template>
      <template v-slot:items="props">
        <td>{{props.item.contestant.bib}}</td>
        <td>{{props.item.contestant.name}}</td>
        <td>{{props.item.contestant.category}}</td>
        <td>{{ durationFormat(props.item.raceDuration) }}</td>
        <td>{{ props.item.meanPaceByKm }}</td>
        <td>{{ props.item.contestant.club }}</td>
      </template>
    </v-data-table>
  </v-card>
</template>

<style scoped>

</style>
