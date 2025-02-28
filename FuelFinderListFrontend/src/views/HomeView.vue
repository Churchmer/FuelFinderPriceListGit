<template>
  <div class="Home">
    <PageHeader />
    <FilterSeettings />
    <main>
      <div class="first-list-element">
        <GasStationListElement />
      </div>
      <GasStationListElement />
      <GasStationListElement />
      <GasStationListElement />
      <GasStationListElement />
      <GasStationListElement />
      <GasStationListElement />
    </main>
  </div>
</template>

<script setup>
  import PageHeader from '@/components/PageHeader.vue'
  import FilterSeettings from '@/components/FilterSeettings.vue';
  import GasStationListElement from '@/components/GasStationListElement.vue';
</script>

<style>
  .first-list-element {
    margin-top: 30px;
  }
</style>
