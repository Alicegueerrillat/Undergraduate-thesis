<template>
  <div class="content-wrapper">
    <RouterView v-slot="{ Component }">
      <component :is="Component" :key="$route.fullPath" />
    </RouterView>
  </div>
</template>
