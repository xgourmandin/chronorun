<script>
  import {mapState} from 'vuex'

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
          {text: 'Temps', value: 'raceTime'},
          {text: 'Dossard', value: 'bib'},
        ],
        pagination: {
          rowsPerPage: 25
        },
      }
    },
    computed: {
      ...mapState( {
        raceTimes: (state) => state.results.markedTimes,
      }),
    },
    methods: {
      timeFormat: function (millis) {
        let time = new Date(millis)
        return time.getHours() + "H" + time.getMinutes() + "M" + time.getSeconds() + "s"
      },
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
      :items="raceTimes"
      :search="search"
      :pagination.sync="pagination"
    >
      <template v-slot:no-data>
        Pas de temps disponible
      </template>
      <template v-slot:items="props">
        <td>{{ timeFormat(props.item.time) }}</td>
        <td><v-text-field type="number"></v-text-field></td>
      </template>
    </v-data-table>
  </v-card>
</template>

<style scoped>

</style>
