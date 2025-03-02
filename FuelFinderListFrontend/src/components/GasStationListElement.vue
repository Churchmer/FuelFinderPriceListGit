<script setup>
    // Define prop to store gas stations in it
    const props = defineProps({
    stations: {
        type: Array,
        default: () => []
    }
    });
</script>

<template> 
    <!-- Conditional rendering; check if stations data is available -->
    <div v-if='stations && stations.length > 0'>
         <!-- Loop through each station and generate a list element -->
        <div v-for='(station, index) in stations' :key='index' class='list-element-container'>
            <div class='left-container'>
                <div class='station-brand'> 
                    {{ station.brand || 'Unabhängige Tankstelle' }} 
                </div>
                <div class='adress-container'> 
                    {{ station.street || 'Unbekannte Straße' }}, 
                    {{ station.postalCode || 'unbekannte Postleitzahl' }}, 
                    {{ station.place || 'unbekannter Ort'}}
                </div>
            </div>
            <div class='center-container'> 
                {{ station.distance }} 
                <span class='km-text'>Km</span>
            </div>
            <div class ='right-container'> 
                {{ station.fuelPrice }} € 
            </div>
        </div>
    </div>
    <!-- Fallback content if stations data is unavailable -->
    <div v-else class='list-element-container'>
        <p>Keine Daten verfügbar.</p>
    </div>

</template>

<script>
    export default {
        name: 'GasStationListElement',
    }
</script>

<style scoped>
    .list-element-container {
        margin-left: 25%;
        margin-right: 25%;
        border: 1px solid rgb(59, 59, 59);
        border-radius: 8px;
        padding: 16px;
        margin-bottom: 4px;
        display: flex;
        align-items: center;
        position: relative;
        justify-content: space-between;
        font-family: Tahoma, Verdana, Segoe, sans-serif;
    }

    .list-element-container:hover {
        border: 3px solid yellow;
    }

    /* Flex settings for equal spacing across sections */
    .left-container, .center-container, .right-container {
        flex: 1;
    }
    
    .center-container {
        text-align: center;
    }

    .km-text {
        font-size: small;
    }

    .right-container {
        text-align: right;
    }

    .adress-container {
        font-size: small;
        font-style: normal;
    }

</style>