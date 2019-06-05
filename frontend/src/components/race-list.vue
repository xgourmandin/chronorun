<script>
  import {mapState} from 'vuex'

  export default {
    data() {
      return {
        search: '',
        headers: [
          {text: 'Nom', value: 'name'},
          {text: 'Distance', value: 'distance'},
          {text: 'Date', value: 'raceDate'},
          {text: 'Actions', value: 'act', sortable: false}
        ],
        pagination: {
          rowsPerPage: 10
        },
      }
    },
    computed: {
      ...mapState({
        races: state => state.races
      })
    },
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
      :items="races"
      :search="search"
      :pagination.sync="pagination"
    >
      <template v-slot:no-data>
        Pas de course enregistré
      </template>
      <template v-slot:items="props">
        <td>{{ props.item.name }}</td>
        <td>{{ props.item.distance }}</td>
        <td>{{ props.item.raceDate }}</td>
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
            class="mr-2"
            @click="deleteItem(props.item)"
          >
            delete
          </v-icon>
          <v-icon
            small
            @click="deleteItem(props.item)"
          >
            assignment
          </v-icon>
        </td>
      </template>
    </v-data-table>
  </v-card>
</template>