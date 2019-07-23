<script>
  import {mapState} from 'vuex'
  import {padNumber} from "../utils/number-utils";

  export default {
    data: function () {
      return {
        search: '',
        headers: [
          {text: 'Heure d\'arrivée', value: 'raceTime'}
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
        return padNumber(time.getHours()) + "H" + padNumber(time.getMinutes()) + "M" + padNumber(time.getSeconds()) + "s"
      },
    }
  }
</script>

<template>
  <v-card>
    <v-card-title primary-title><b>Temps marqués disponibles : {{raceTimes.length}}</b></v-card-title>
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
        <td>{{ timeFormat(props.item) }}</td>
      </template>
    </v-data-table>
  </v-card>
</template>

<style scoped>

</style>
