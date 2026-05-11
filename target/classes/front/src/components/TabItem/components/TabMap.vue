<script setup>
/**
 * @description iframe地图
 */
import { inject } from 'vue'
defineOptions({
  inheritAttrs: false,
})

let { table } = inject('detail')
let { mapType } = table
const mapType_src_map = {
  百度: 'https://map.baidu.com/',
  腾讯: 'https://map.qq.com/',
  高德: 'https://www.amap.com/',
  百度天气: 'http://www.weather.com.cn/forecast/index_mapFull.shtml',
  天地图: 'https://www.tianditu.gov.cn/coronavirusmap/',
}
let src = mapType_src_map[mapType]
</script>

<template>
  <div class="quill-tab">
    <div>
      <iframe
        MARGINWIDTH="0"
        MARGINHEIGHT="0"
        HSPACE="0"
        VSPACE="0"
        FRAMEBORDER="0"
        SCROLLING="no"
        :src="src"
        height="600px"
        width="100%"
      ></iframe>
    </div>
  </div>
</template>
