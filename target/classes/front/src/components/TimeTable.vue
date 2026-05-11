<script setup>
/**
 * @description 课程表
 */
// TODO:vm话，没有空白
import { ref } from 'vue'
import { getPageAPI } from '@/api/list'

import tableConfigs from '@/utils/tableConfigs'
const scheduletimeTable = 'scheduletime'
const tableColumns = [
  {
    columnName: '1',
    label: '星期一',
  },
  {
    columnName: '2',
    label: '星期二',
  },
  {
    columnName: '3',
    label: '星期三',
  },
  {
    columnName: '4',
    label: '星期四',
  },
  {
    columnName: '5',
    label: '星期五',
  },
  {
    columnName: '6',
    label: '星期六',
  },
  {
    columnName: '7',
    label: '星期日',
  },
]
// 配置项
let {
  titleNames = [],
  subTitleNames = [],
  searchNames = [],
} = tableConfigs[tableName].timeTable || {}

defineOptions({
  inheritAttrs: false,
})
const { tableName } = defineProps(['tableName'])

const isLoading = ref(false)
const tableData = ref([])

fetchData()
/**
 * 节次数据 [{"sectionnum": 1,"starttime": "08:00","endtime": "09:00" }]
 * 课程数据 [{"kechengmingcheng": "课程名称1","sectionnum": "1","week": 1}]
 * 整合成: [{"sectionnum": 1,"starttime": "08:00","endtime": "09:00", "1": {"kechengmingcheng": "课程名称1"}, "2": null,"3": null, "4": null }]
 */
async function fetchData() {
  isLoading.value = true
  try {
    // [1] 获取课表时间数据 (一天有多少节)
    let jcParams = {
      limit: 999,
      order: 'asc',
      // 按节次排序-升序
      sort: 'sectionnum',
    }
    let jcRes = await getPageAPI(scheduletimeTable, jcParams)
    let jcList = jcRes.data.list
    if (!jcList.length) {
      throw new Error('没有配置课表时间')
    }

    // // 新增cellSpan字段 (时间段 合并单元格的 数据逻辑)
    // mergeTimeCell(jcList)

    // 把jcList扁平化,方便后续 遍历kbList是整合数据
    let jcDatas = {}
    jcList.forEach(item => {
      jcDatas[item.sectionnum] = item
    })

    // [2] 获取课表信息数据 (当前配置课程表的)(这个星期的课程)

    let kbParams = {
      limit: 9999,
    }
    let userForm = JSON.parse(localStorage.getItem('userForm'))
    searchNames.forEach(name => {
      // 过滤，如果用户是 班级一 ，那么只显示 班级一 的数据
      if (userForm[name]) {
        kbParams[name] = userForm[name]
      }
    })
    let kbRes = await getPageAPI(tableName, kbParams)
    let kbList = kbRes.data.list

    // // [3] 合并成表格数据（往jsList的每个数据上添加,1,2,3,4,5,6,7属性的值
    kbList.forEach(item => {
      // 根据sectionnum字段(多选类型)，确定节次
      if (item.sectionnum) {
        let sectionnums = item.sectionnum.split(',')

        sectionnums.forEach(sectionnum => {
          let jcData = jcDatas[sectionnum]
          if (jcData) {
            // 根据week字段，确定星期几
            jcData[item.week] = item
          }
        })
      }
    })

    tableData.value = jcList
  } catch (error) {}
  isLoading.value = false
}

// 多节次的课程，合并
function spanMethod({ row, column, rowIndex, columnIndex }) {
  // 节次不合并
  if (columnIndex == 0) {
    return
  }

  // 没有课程，不合并
  let kb = row[columnIndex]
  if (!kb) {
    return
  }

  // 没有2节次以上的课程，不合并
  let sectionnums = kb.sectionnum.split(',')
  let length = sectionnums.length
  if (length < 2) {
    return
  }

  // ['1','2'] 从小到大排序
  sectionnums = sectionnums.map(item => parseInt(item))
  sectionnums.sort((a, b) => a - b)

  // 占多行1列
  if (rowIndex + 1 === sectionnums[0]) {
    return [length, 1]
  } else {
    return [0, 0]
  }
}
</script>

<template>
  <div class="timetable-page">
    <el-table
      class="time-table"
      :data="tableData"
      v-loading="isLoading"
      show-overflow-tooltip
      border
      size="small"
      row-key="sectionnum"
      :span-method="spanMethod"
    >
      <el-table-column prop="sectionnum" label="节次" align="center">
        <template #default="scope">
          <div>{{ scope.row.sectionnum }}</div>
          <div>{{ scope.row.starttime }}-{{ scope.row.endtime }}</div>
        </template>
      </el-table-column>
      <el-table-column
        v-for="item in tableColumns"
        :key="item.columnName"
        :label="item.label"
        align="center"
      >
        <template #default="scope">
          <template v-if="scope.row[item.columnName]">
            <div class="title" v-for="i in titleNames" :key="i">
              {{ scope.row[item.columnName][i] }}
            </div>
            <div class="text" v-for="i in subTitleNames" :key="i">
              {{ scope.row[item.columnName][i] }}
            </div>
          </template>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style lang="scss">
.timetable-page {
  padding: 20px;
}
.time-table.el-table .cell {
  line-height: 24px;
}
.time-table {
  .title {
    font-weight: 700;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
  }
}
</style>
