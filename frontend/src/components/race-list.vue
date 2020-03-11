<script>
  import {mapState} from 'vuex'
  import EventBus from "../event-bus"
  import store from '../store'
  import ValidatingButton from "./validating-button";

  export default {
    components: {ValidatingButton},
    data() {
      return {
        search: '',
        headers: [
          {text: 'Nom', value: 'name'},
          {text: 'Distance', value: 'distance'},
          {text: 'Date', value: 'raceDate'},
          {text: 'Actions', value: 'actions', sortable: false, filterable: false, width: "20em"}
        ],
        pagination: {
          itemsPerPage: 10
        },
      }
    },
    computed: {
      ...mapState({
        races: state => state.race.races
      })
    },
    mounted() {
      store.dispatch('loadRaces')
    },
    methods: {
      editItem: function (race) {
        EventBus.$emit('edit_race', race)
      },
      deleteItem: function (race) {
        store.dispatch('deleteRace', race)
      },
      getResults: function (race) {
        window.open("api/race/print/" + race.id, "_blank")
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
        append-icon="mdi-magnify"
        label="Chercher"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>

    <v-data-table
      :headers="headers"
      :items="races"
      :search="search"
      :options="pagination"
    >
      <template v-slot:no-data>
        Pas de course enregistré
      </template>
      <template v-slot:item.actions="{ item }">
        <v-icon
          small
          class="mr-2"
          @click="editItem(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
          small
          @click="getResults(item)"
        >
          mdi-printer
        </v-icon>
        <validating-button color="error" confirm-count="2" action-text="Supr"
                           @click-validated="deleteItem(item)"></validating-button>
      </template>
    </v-data-table>
  </v-card>
</template>

<style scoped>

  button {
    margin-left: 2em;
  }

</style>