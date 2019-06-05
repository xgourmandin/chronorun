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
        columns: [
          {name: "id"},
          {name: "temps", appliedFilter: this.timeFormat},
          {name: "dossard", type:"input"}
        ],
      }
    },
    computed: {
      ...mapState('race', {
        raceTimes: (state) => state.markedTimes,
      }),
    },
    methods: {
      timeFormat: function (millis) {
        let time = new Date(millis)
        return time.getUTCHours() + "H" + time.getMinutes() + "M" + time.getSeconds() + "s"
      },
      renderCell: function (column, data) {
        if (column.appliedFilter) {
          return column.appliedFilter(data[column.name])
        }
        else {
          return data[column.name]
        }
      }
    }
  }
</script>

<template>
  <table>
    <thead>
    <tr>
      <th v-for="key in columns" :key="key.name">
        {{ key.name | capitalize }}
      </th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="entry in raceTimes" :key="entry.id">
      <td v-for="key in columns" :key="key.name ">
        <span v-if="key.type && key.type === 'input'">
          <BaseInputText type="number" :value="entry[key.name]" min="1"></BaseInputText>
        </span>
        <span v-else>
          {{renderCell(key, entry)}}
        </span>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<style scoped>

  table {
    width: 100%;
    background-color: #fff;
    border: 2px solid #42b983;
    border-radius: 3px;
  }

  th {
    color: rgb(255, 255, 255);
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-color: indigo;
  }

  td {
    background-color: #f9f9f9;
  }

  th, td {
    min-width: 120px;
    padding: 10px 20px;
  }

</style>
