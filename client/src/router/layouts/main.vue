<script>
import NavBar from '@components/nav-bar'

export default {
  components: { NavBar },
}
</script>

<template>
  <div>
    <NavBar />
    <v-app :class="$style.container">
      <slot />
    </v-app>
  </div>
</template>

<style lang="scss" module>
@import '@design';

.container {
  min-width: $size-content-width-min;
  max-width: $size-content-width-max;
  margin: 0 auto;
}
</style>
