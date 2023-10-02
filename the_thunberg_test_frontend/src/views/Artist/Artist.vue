<template>
  <div class="Artist">
    <Person :id="id"></Person>
    <Credit :id="id"></Credit>
  </div>
</template>

<script>
import Person from "./Person.vue";
import Credit from "./Credit.vue";

export default {
  name: "Artist",
  
  components: {
    Person,
    Credit,
  },
  data() {
    return {
      id: this.$route.query.id,
    };
  },
};
</script>