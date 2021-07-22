<template>
  Search <input name="query" v-model="searchQuery">

  <button v-on:click="getData">getData</button>

  <sample-grid
      :heroes="gridData"
      :columns="gridColumns"
      :filter-key="searchQuery">
  </sample-grid>
</template>

<script>
import SampleGrid from "@/components/SampleGrid";

export default {
  name: 'App',
  components: {
    SampleGrid,
  },
  data: function () {
    return {
      searchQuery: '',
      gridColumns: ['name', 'hp', 'type', 'card'],
      gridData: []
    }
  },
  created: function () {
    this.getData()
  },
  methods: {
    getData: function () {
      var xhr = new XMLHttpRequest();
      var self = this;
      xhr.open('GET', 'inquiry');
      xhr.onload = function () {
        self.gridData = JSON.parse(xhr.responseText);
      };
      xhr.send();
    }
  }
}
</script>

<style>
body {
  font-family: Helvetica Neue, Arial, sans-serif;
  font-size: 14px;
  color: #444;
}
</style>
