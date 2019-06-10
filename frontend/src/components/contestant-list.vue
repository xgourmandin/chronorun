<script>
  import {mapState} from 'vuex'
  import store from '../store'
  import EventBus from '../event-bus'

  export default {
    data() {
      return {
        search: '',
        headers: [
          {text: 'Dossard', value: 'bib'},
          {text: 'Nom', value: 'name'},
          {text: 'Naissance', value: 'birthYear'},
          {text: 'Catégorie', value: 'category'},
          {text: 'Sexe', value: 'sex'},
          {text: 'Actions', value: 'act', sortable: false}
        ],
        pagination: {
          rowsPerPage: 10
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
        append-icon="search"
        label="Chercher"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>

    <v-data-table
      :headers="headers"
      :items="contestants"
      :search="search"
      :pagination.sync="pagination"
    >
      <template v-slot:no-data>
        Pas encore d'inscrit
      </template>
      <template v-slot:items="props">
        <td>{{ props.item.bib }}</td>
        <td>{{ props.item.name}}</td>
        <td>{{ props.item.birthYear }}</td>
        <td>{{ props.item.category }}</td>
        <td>{{ props.item.sex }}</td>
        <td class="justify-center layout px-0">
          <v-icon
            small
            class="mr-2"
            @click="editItem(props.item)"
          >
            edit
          </v-icon>
          <v-icon
            small
            @click="deleteItem(props.item)"
          >
            delete
          </v-icon>
        </td>
      </template>
    </v-data-table>
  </v-card>
</template>

