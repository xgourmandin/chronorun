<script>
  import CategoryCard from "./category-card";
  import store from '@state/store'
  import {mapState} from 'vuex'

  export default {
    components: {CategoryCard},
    data() {
      return {
        category: '',
      }

    },
    computed: {
      ...mapState('race', {
        categories: (state) => state.raceParams.categories
      })
    },
    methods: {
      addCategory: function () {
        if (this.category !== '') {
          store.dispatch('race/addCategory', {category: this.category})
          this.category = ''
        }
      },
      deleteCategory(cat) {
        if (cat) {
          store.dispatch('race/deleteCategory', {category: cat})
        }
      },
      saveParams: function () {

      }
    }
  }
</script>

<template>
  <div>
    <div :class="$style.halfpage">
      <label for="category">Catégories</label>
      <BaseInputText
        id="category"
        v-model="category"
        name="category"
        :placeholder="'Catégorie de course'"
        @keyup.enter="addCategory"
      />
      <BaseButton @click="addCategory">Ajouter</BaseButton>
    </div>
    <div :class="$style.halfpage">
      <h5>Cat&eacute;gories disponible</h5>
      <CategoryCard v-for="cat in categories" :key="cat" :name="cat" :class="$style.inlinecard"
                    @delete-category="deleteCategory"
      ></CategoryCard>
    </div>
  </div>
</template>

<style lang="scss" module>
  @import '@design';

  .halfpage {
    display: inline-table;
    width: 50%;
    margin-right: 1em;
  }

  .halfpage:nth-last-child(1) {
    width: 48%;
    margin-right: 0;
  }

  .halfpage > label {
    display: block;
    padding-bottom: 0.25em;
    color: #35495e;
  }

  .inlinecard {
    display: inherit;
    margin-right: 1em;
  }
</style>
