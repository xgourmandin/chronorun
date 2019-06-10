<script>

  export default {
    inheritAttrs: false,
    props: ['confirmCount', 'actionText'],
    data() {
      return {
        clickCount: 0,
        confirmDialog: ['Sûr ?', 'Vraiment ?', 'Dernière chance ?'],
        displayedText: this.actionText
      }
    },
    methods: {
      validateClick: function(e) {
        e.preventDefault();
        if (this.clickCount > this.confirmCount){
          this.displayedText = this.actionText
          this.clickCount = 0
          this.$emit('click-validated', e)
        }
        else {
          this.displayedText = this.confirmDialog[this.clickCount]
          this.clickCount++
        }
      }
    }
  }

</script>

<template>
  <v-btn v-bind="$attrs" @click="validateClick">{{ displayedText }}</v-btn>
</template>