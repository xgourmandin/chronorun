<script>
  import {mapState} from 'vuex'
  import store from '../store'
  import ValidatingButton from "./validating-button";
  import EventBus from '../event-bus'

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
        ],
        pagination: {'sortBy': 'raceTime', 'descending': false, 'rowsPerPage': 25}
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
      },
      deleteResult: function (idTodelete) {
        store.dispatch('deleteResult', idTodelete)
      },
      editItem: function (result) {
        EventBus.$emit('edit_result', result)
      },
      customSort: function (items, index, isDesc) {
        items.sort((a, b) => {
          if (index === 'raceTime') {
            if (a.raceTime === null) return 1
            if (b.raceTime === null) return -1
            return this.compareString(a[index], b[index], isDesc)
          }
          return this.compareString(a[index], b[index], isDesc)
        })
        return items
      },
      compareString(a, b, isDesc) {
        if (isDesc) {
          return b.localeCompare(a)
        }else {
          return a.localeCompare(b)
        }
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
      :custom-sort="customSort"
    >
      <template v-slot:no-data>
        Pas de résultats disponible
      </template>
      <template v-slot:items="props">
        <td>{{props.item.contestant.bib}}</td>
        <td>{{props.item.contestant.name}}</td>
        <td>{{props.item.contestant.category}}</td>
        <td v-if="props.item.contestant.gaveUp">Abandon</td>
        <td v-else>{{ durationFormat(props.item.raceDuration) }}</td>
        <td v-if="props.item.contestant.gaveUp">N/A</td>
        <td v-else>{{ props.item.meanPaceByKm }}</td>
        <td>{{ props.item.contestant.club }}</td>
        <td class="justify-center layout px-0">
          <v-icon
            small
            class="mr-2"
            @click="editItem(props.item)"
          >
            edit
          </v-icon>
          <validating-button color="error" confirm-count="0" action-text="Supr" @click-validated="deleteResult(props.item.id)"></validating-button>
        </td>
      </template>
    </v-data-table>
  </v-card>
</template>

<style scoped>

</style>
