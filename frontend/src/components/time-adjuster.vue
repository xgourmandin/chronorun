<template>
  <div v-bind="$attrs">
    <label for="timeadjust" class="inline">Ajustement du temps &nbsp;</label>
      <v-select
        id="timeadjust"
        v-model="adjustOperator"
        :items="adjustOperators"
        class="operatorSelect inline"
        :disabled="disabled"
      @change="changeOperator">
      </v-select>
      <v-text-field v-model="timeAmount" type="number" class="time-text inline" @change="changeAmount" :disabled="disabled"></v-text-field>
      <v-select
        v-model="timeUnit"
        :items="timeUnits"
        class="operatorSelect inline"
      @change="changeUnit"
        :disabled="disabled">
      </v-select>
  </div>
</template>

<script>
  export default {
    name: "time-adjuster",
    props: ['disabled'],
    data() {
      return {
        adjustOperator: '-',
        adjustOperators: ['-', '+'],
        timeAmount: null,
        timeUnit: 'secondes',
        timeUnits: ['secondes', 'minutes'],
        adjustParams: {operator: '-', amount: null, unit: 'secondes'}
      }
    },
    model: {
      prop: 'adjustParams',
      event: 'change'
    },
    watch: {
      disabled: function (newVal) {
        if (newVal) {
          this.cleanForm()
        }
      }
    },
    methods: {
      changeOperator: function () {
        this.adjustParams.operator = this.adjustOperator
        this.$emit('change', this.adjustParams)
      },
      changeAmount: function () {
        this.adjustParams.amount = this.timeAmount
        this.$emit('change', this.adjustParams)
      },
      changeUnit: function () {
        this.adjustParams.unit = this.timeUnit
        this.$emit('change', this.adjustParams)
      },
      cleanForm() {
        this.adjustOperator = '-'
        this.timeAmount = null
        this.timeUnit = 'secondes'
      }
    }
  }
</script>

<style scoped>

  .inline {
    display: inline-block;
    margin-right: 1em;
  }

  .time-text {
    width: 15%;
  }

  .operatorSelect {
    width: 2em;
  }
</style>