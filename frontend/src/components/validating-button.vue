<script>

  export default {
    inheritAttrs: false,
    props: ['confirmCount', 'actionText'],
    data() {
      return {
        clickCount: 0,
        confirmDialog: ['Sûr ?', 'Vraiment ?', 'Dernière chance ?'],
        displayedText: this.actionText,
        timeout: null
      }
    },
    methods: {
      validateClick: function(e) {
        e.preventDefault();
        if (this.clickCount > this.confirmCount){
          clearTimeout(this.timeout)
          this.displayedText = this.actionText
          this.clickCount = 0
          this.$emit('click-validated', e)
        }
        else {
          this.displayedText = this.confirmDialog[this.clickCount]
          this.clickCount++
          if(this.timeout) {
            clearTimeout(this.timeout)
          }
          this.timeout = setTimeout(this.reverseOneStep, 1000)
        }
      },
      reverseOneStep: function() {
        this.clickCount--
        if (this.clickCount > 0) {
          this.displayedText = this.confirmDialog[this.clickCount-1]
          this.timeout = setTimeout(this.reverseOneStep, 1000)
        }
        else {
          this.displayedText = this.actionText
        }
      }
    }
  }

</script>

<template>
  <v-btn v-bind="$attrs" @click="validateClick">{{ displayedText }}</v-btn>
</template>