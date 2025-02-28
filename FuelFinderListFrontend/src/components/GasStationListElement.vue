<script setup>
    defineProps(['gasStationName', 'gasStationLocation', 'distance', 'fuelPrice'])
</script>

<template>
    <div class="list-element-box">
        <div>
            <div>
                {{ gasStationName }}
            </div>
            <div>
                {{ gasStationLocation }}
            </div>
        </div>
        <div>
            {{ distance }}
        </div>
        <div>
            {{ fuelPrice }}
        </div>
    </div>
</template>

<script>
    export default {
        name: 'GasStationListElement',
        data() {
            return {
                gasStationName: 'Test Tankstelle',
                gasStationLocation: 'Teststandort',
                distance: '10km',
                fuelPrice: '1,80€'
            }
        }
    }
</script>

<style scoped>
    .list-element-box {
        margin-left: 25%;
        margin-right: 25%;
        border: 1px solid rgb(59, 59, 59);
        border-radius: 8px;
        padding: 16px;
        margin-bottom: 4px;
        display: flex;
        gap: 33%;
    }
    .list-element-box:hover{
        border: 3px solid yellow;
    }

</style>