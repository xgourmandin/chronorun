<script>
  import {mapState} from 'vuex'
  import store from '../store'
  import ValidatingButton from "./validating-button";
  import EventBus from '../event-bus'
  import {formatDuration} from '../utils/duration'

  export default {
    components: {ValidatingButton},
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
          { text: 'Actions', value: 'actions', sortable: false, filterable: false, width: "15em" }
        ],
        pagination: {itemsPerPage: 15}
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
      durationFormat: function (duration) {
        return formatDuration(duration);
      },
      deleteResult: function (idTodelete) {
        store.dispatch('deleteResult', idTodelete)
      },
      editItem: function (result) {
        EventBus.$emit('edit_result', result)
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
        append-icon="mdi-magnify"
        label="Chercher"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>

    <v-data-table
      :headers="headers"
      :items="raceResults"
      :search="search"
      :sort-by="['raceTime']"
      :sort-desc="[true]"
      :options="pagination"
    >
      <template v-slot:no-data>
        Pas de résultats disponible
      </template>
      <template v-slot:item.raceTime="{ item }">
        {{durationFormat(item.raceTime)}}
      </template>
      <template v-slot:item.actions="{ item }">
          <v-icon
            small
            class="mr-2"
            @click="editItem(item)"
          >
            mdi-pencil
          </v-icon>
          <validating-button color="error" confirm-count="0" action-text="Supr" @click-validated="deleteResult(item.id)"></validating-button>
      </template>
    </v-data-table>
  </v-card>
</template>

<style scoped>

  button {
    margin-right: 2em;
  }
</style>
