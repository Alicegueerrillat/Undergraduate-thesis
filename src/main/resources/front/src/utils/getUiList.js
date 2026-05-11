/**
 * @description UI组件(View.vue后缀文件)的数据获取
 */

import { getFirstFilePath } from './getFilePath'

export default function getUiList(config, list) {
  // 字段配置项
  let { hasIsanon, imgName, pubPeopleName, titleNames, titleHeads } = config

  let uiList = list.map(item => {
    return {
      id: item.id,
      img: getFirstFilePath(item[imgName]),
      user: hasIsanon && item.isanon == 1? '匿名' : item[pubPeopleName],
      titles: titleNames.map(
        (name, index) => `${titleHeads[index] ? titleHeads[index] + ': ' + item[name] : item[name]}`
      ),
      introduction: item.introduction,
      price: item.price,
      addtime: item.addtime,
      thumbsupnum: item.thumbsupnum,
      storeupnum: item.storeupnum,
      clicknum: item.clicknum,
      browseduration: item.browseduration,
    }
  })

  return uiList
}
