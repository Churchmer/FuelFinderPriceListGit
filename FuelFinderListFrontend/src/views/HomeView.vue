<script setup>
  import PageHeader from '@/components/PageHeader.vue'
  import FilterSettings from '@/components/FilterSettings.vue';
</script>

<template>
  <!-- Start page structure with header and main content -->
  <div class="Home">
    <PageHeader />
    <main>
      <FilterSettings />
    </main>
  </div>
</template>