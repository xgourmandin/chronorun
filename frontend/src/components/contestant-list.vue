<script>
  import {mapState} from 'vuex'
  import store from '../store'
  import EventBus from '../event-bus'
  import ValidatingButton from "./validating-button";

  export default {
    components: {ValidatingButton},
    data() {
      return {
        search: '',
        headers: [
          {text: 'Dossard', value: 'bib'},
          {text: 'Nom', value: 'name'},
          {text: 'Naissance', value: 'birthYear'},
          {text: 'Catégorie', value: 'category'},
          {text: 'Sexe', value: 'sex'},
          {text: 'Actions', value: 'actions', sortable: false, width: "15em"}
        ],
        pagination: {
          itemsPerPage: 15
        },
      }
    },
    computed: {
      ...mapState({
        contestants: state => state.contestant.contestants
      })
    },
    mounted() {
      store.dispatch('loadContestants')
    },
    methods: {
      editItem: function (contestant) {
        EventBus.$emit('edit_contestant', contestant)
      },
      deleteItem: function (contestant) {
        store.dispatch('deleteContestant', contestant)
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
      :items="contestants"
      :search="search"
      :options="pagination"
    >
      <template v-slot:no-data>
        Pas encore d'inscrit
      </template>
      <template v-slot:item.actions="{ item }">
          <v-icon
            small
            class="mr-2"
            @click="editItem(item)"
          >
            mdi-pencil
          </v-icon>
          <validating-button color="error" confirm-count="0" action-text="Supr" @click-validated="deleteItem(item)"></validating-button>
      </template>
    </v-data-table>
  </v-card>
</template>

