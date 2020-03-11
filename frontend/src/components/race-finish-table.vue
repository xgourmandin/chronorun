<script>
  import {mapState} from 'vuex'
  import store from '../store'
  import ValidatingButton from "./validating-button"

  export default {
    components: {ValidatingButton},
    data: function () {
      return {
        search: '',
        headers: [
          {text: 'Heure d\'arrivée', value: 'raceTime'},
          {text: 'Actions', value: 'act', sortable: false}
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
      deleteItem: function(item) {
        store.dispatch("deleteMark", item)
      }
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
        <td>{{ props.item.mark }}</td>
        <td>
          <validating-button color="error" confirm-count="0" action-text="Supr" @click-validated="deleteItem(props.item)"></validating-button>
        </td>
      </template>
    </v-data-table>
  </v-card>
</template>

<style scoped>

</style>
