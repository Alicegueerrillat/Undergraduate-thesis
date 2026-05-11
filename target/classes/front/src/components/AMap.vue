<script setup>
/**
 * @description 高德地图
 * mode: Address 地址拾取  Driving 地图路线
 */
import { onMounted, onUnmounted, ref } from 'vue'
import AMapLoader from '@amap/amap-jsapi-loader'

const emits = defineEmits(['getAddress'])
const { data } = defineProps(['data'])
let { mode = 'Address', posColumns, row } = data

let map = null,
  geocoder = null,
  placeSearch = null
function initMap() {
  window._AMapSecurityConfig = {
    securityJsCode: 'e98cb861b423bafe637b87497dce427f',
  }
  AMapLoader.load({
    key: '6c904b65d38915e90eae644d1600bb3a',
    version: '2.0',
    plugins: ['AMap.Scale', 'AMap.Geocoder', 'AMap.PlaceSearch', 'AMap.Driving'],
  })
    .then(AMap => {
      map = new AMap.Map('map', {
        zoom: 11,
      })

      geocoder = new AMap.Geocoder({})

      if (mode == 'Address') {
        initEvent_Address(AMap)
        return
      }

      if (mode == 'Driving') {
        initEvent_Driving(AMap)
        return
      }
    })
    .catch(e => {
      console.log(e)
    })
}

// ----------------------------------
// ---------- 地址拾取 ---------------
// ----------------------------------
const search = ref('')
function initEvent_Address(AMap) {
  //构造地点查询类
  placeSearch = new AMap.PlaceSearch({
    pageSize: 5, // 单页显示结果条数
    pageIndex: 1, // 页码
    map: map,
    panel: 'panel',
    autoFitView: true, // 是否自动调整地图视野使绘制的 Marker点都处于视口的可见范围
  })

  // 点击拾取
  AMap.Event.addListener(placeSearch, 'markerClick', e => {
    getAddress(e.data.location)
  })

  // 点击拾取
  map.on('click', e => {
    getAddress(e.lnglat)
  })
}
function getAddress(lnglat) {
  geocoder.getAddress(lnglat, (status, result) => {
    if (status === 'complete' && result.regeocode) {
      let address = result.regeocode.formattedAddress
      emits('getAddress', {
        lat: lnglat.lat,
        lng: lnglat.lng,
        address,
      })
    } else {
      ElMessage.error('地址拾取失败！')
    }
  })
}
function searchEvent() {
  if (!search.value) {
    clearEvent()
    return
  }
  placeSearch.search(search.value)
}
function clearEvent() {
  placeSearch.clear()
}

// ----------------------------------
// ---------- 地图路线 ---------------
// ----------------------------------
const time = ref('')
const distance = ref('')
async function initEvent_Driving(AMap) {
  let driving = new AMap.Driving({
    policy: 0, //驾车路线规划策略，0是速度优先的策略
    map: map,
  })

  let points = posColumns.map(column => {
    return {
      keyword: row[column.columnName] ? row[column.columnName].split(';')[2] : '',
    }
  })
  driving.search(points, function (status, result) {
    switch (status) {
      case 'complete':
        ElMessage.success('查询成功')
        time.value = getTime(result.routes[0].time)
        distance.value = getDistance(result.routes[0].distance)
        break

      case 'no_data':
        ElMessage.warning('查询无结果')
        break

      case 'error':
        ElMessage.error('查询错误')
        break
    }
  })
}
function getDistance(num) {
  if (num < 1000) {
    return num + '米'
  }
  return (num / 1000).toFixed(2) + '公里'
}
function getTime(num) {
  if (num < 60) {
    return num + '秒'
  }
  if (num < 3600) {
    let minutes = Math.floor((num % 3600) / 60)
    return minutes + '分钟'
  }
  let hours = Math.floor(num / 3600)
  let minutes = Math.floor((num % 3600) / 60)
  return hours + '小时' + minutes + '分钟'
}

onMounted(() => {
  initMap()
})
onUnmounted(() => {
  placeSearch?.clear()
  map?.destroy()
})
</script>

<template>
  <div class="map-container" id="map">
    <div class="map-search-wrapper">
      <template v-if="mode == 'Address'">
        <!-- 搜索 -->
        <el-input v-model="search" class="search-input" clearable @clear="clearEvent">
          <template #append>
            <el-button icon="Search" @click="searchEvent" />
          </template>
        </el-input>

        <div id="panel"></div>
      </template>
      <div class="driving" v-else>
        <span>耗时：{{ time }}</span>
        <span>路长：{{ distance }}</span>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
.map-container {
  width: 100%;
  height: 60vh;
  position: relative;
  .map-search-wrapper {
    position: absolute;
    left: 0;
    top: 0;
    width: auto;
    z-index: 9;
  }
  .driving {
    background-color: #fff;
    padding: 10px;
    border-radius: 10px;
    margin: 10px 0 0 10px;
    display: flex;
    gap: 20px;
  }
}
</style>
