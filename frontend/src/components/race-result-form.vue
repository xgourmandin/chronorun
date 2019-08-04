<template>
    <form
  @submit.prevent="saveResult">

      <div class="halfpage">
        <v-text-field
          id="bib"
          v-model="bib"
          label="Dossard"
          type="number"
          name="bib"
          class="smallinput"
        />
      </div>

      <time-adjuster class="halfpage" v-model="adjustParam" :disabled="!editMode"></time-adjuster>

      <validating-button color="success" type="submit" action-text="Editer" @click-validated="saveResult" confirm-count="0"></validating-button>
      <v-btn color="warning" @click="cancelEdit" v-if="editMode">Annuler</v-btn>
    </form>
</template>

<script>
    import TimeAdjuster from "./time-adjuster";
    import store from '../store'
    import EventBus from '../event-bus'
    import ValidatingButton from "./validating-button";
    import _ from 'lodash';

    export default {
        name: "race-result-form",
      components: {ValidatingButton, TimeAdjuster},
      data() {
          return {
            id: null,
            bib: null,
            adjustParam: {},
            editMode: false
          }
      },
      computed: {
        form() {
          return {
            resultId: this.id,
            bib: this.bib,
            adjustParam: this.adjustParam,
          }
        }
      },
      mounted() {
        EventBus.$on('edit_result', function (result) {
          this.id = result.id
          this.editMode = true;
          this.bib = result.contestant.bib;
        }.bind(this))
      },
      methods: {
        saveResult: function() {
          let editedResult = this.form
          if (_.isEmpty(editedResult.adjustParam)) {
            delete editedResult.adjustParam
          }
          store.dispatch('updateResult', editedResult).then(() => this.cancelEdit())
        },
        cleanForm() {
          this.id = null
          this.bib = null
          this.adjustParam = {}
        },
        cancelEdit: function () {
          this.editMode = false
          this.cleanForm()
        }
      }
    }
</script>

<style scoped>

  .halfpage {
    display: inline-table;
    width: 50%;
  }

  .halfpage > label {
    display: block;
    padding-bottom: 0.25em;
    color: #35495e;
  }

  .smallinput {
    width: 80%;
  }

</style>