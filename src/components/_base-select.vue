<script>
  export default {
    // Disable automatic attribute inheritance, so that $attrs are
    // passed to the <input>, even if it's not the root element.
    // https://vuejs.org/v2/guide/components-props.html#Disabling-Attribute-Inheritance
    inheritAttrs: false,
    // Change the v-model event name to `update` to avoid changing
    // the behavior of the native `input` event.
    // https://vuejs.org/v2/guide/components-custom-events.html#Customizing-Component-v-model
    model: {
      event: 'update',
    },
    props: {
      choices: {
        type: Array,
        required: true
      }
    }
  }
</script>

<template>
  <select :class="$style.select" v-bind="
      $attrs
      // https://vuejs.org/v2/guide/components-props.html#Disabling-Attribute-Inheritance
    "
    v-on="
      $listeners
      // https://vuejs.org/v2/guide/components-custom-events.html#Binding-Native-Events-to-Components
    ">
    <option v-for="choice in choices" :key="choice.value" :value="choice.value">{{choice.label}}</option>
  </select>
</template>

<style lang="scss" module>
  @import '@design';

  .select {
    @extend %typography-small;

    display: block;
    width: 100%;
    padding: 0.5em $size-input-padding-horizontal;
    margin-bottom: $size-grid-padding;
    line-height: 1;
    border: $size-input-border solid $color-input-border;
    border-radius: $size-input-border-radius;
  }
</style>
