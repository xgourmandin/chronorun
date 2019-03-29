<script>
  export default {
    data() {
      return {
        category: '',
        categories: [],
      }

    },
    methods: {
      addCategory: function () {
        if (this.category !== '') {
          this.categories.push(this.category)
          this.category = '';
        }
      }
    }
  }
</script>

<template>
  <div>
      <label for="category">Catégories</label>
      <BaseInputText
        id="category"
        v-model="category"
        name="category"
        :placeholder="'Catégorie de course'"
        @keyup.enter="addCategory"
      />
    <BaseButton @click="addCategory">Ajouter</BaseButton>
    <ul>
      <li v-for="cat in categories" :key="cat">{{cat}}</li>
    </ul>
  </div>
</template>

<style lang="scss" module>
  @import '@design';
</style>
