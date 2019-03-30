<script>
  export default {
    model: {
      event: 'delete-category '
    },
    props: {
      name: {
        type: String,
        required: true
      }
    },
    methods: {
      propagateClick() {
        this.$emit('delete-category', this.name)
      }
    }
  }
</script>

<template>
  <div :class="$style.categoryCard">
    {{name}}
    <BaseIcon name="trash" size="xs" :class="$style.clickable" @click.native="propagateClick"/>
  </div>
</template>

<style lang="scss" module>
  @import '@design';

  .categoryCard {
    color: #f9f7f5;
    width: max-content;
    background-color: #35495e;
    border-radius: 10%;
    margin: 0.25em 0;
    padding: 0.25em 0.5em;
  }

  .clickable {
    cursor: pointer;
  }

</style>
