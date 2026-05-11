export let table = {
  tableName: 'zhuanye',
  comment: '',
  comments: '专业',
  sfsh: '否',
  authPeople: '否',
  emailRegister: '否',
  hasCart: '否',
  thumbsUp: '否',
  storeUp: '否',
  browseClick: '否',
  hasHot: '无',
  virtualPay: '否',
  hasSales: false,
  selectSeat: '否',
  hasPay: '否',
  isReverse: '否',
  browseHistory: '否',
  limit: '否',
  vip: '否',
  jifen: '否',
  hasAlipay: '否',

  groupBuy: '否',
  creditScore: '否',
  authSeparate: '否',
  discuss: '否',
  location: '否',
  sessionReadTable: '',
  merchantRole: '',
  homeSearch: '否',
  mapType: '无',
  hasChapter: '无',
  hasAuction: '否',
  hasMusic: '否',
  sharePlatform: '',
  hasChat: '无',
  collaborativeAction: '',
  fkMainTable: '',
  status: '',
  crossOptTableName: [],
  crossOptButton: [],
  crossOptAudit: [],
  crossOptPay: [],
  crossOptButtonStatusColumns: [],
  crossOptButtonTips: [],

  csuOperateList: [],
  csuTypeList: [],
  csuUpdateTableList: [],
  csuUpdateColumnList: [],
  csuUpdateColumnTypeList: [],
  csuUpdateColumnValueList: [],
  csuConditionColumnList: [],
  csuMessageList: [],
    
}

export let columns = [
  {
    columnName: 'zhuanye',
    comments: '专业',
    type: '普',
    dateCurFlag: '',
    categoryFlag: '',
    refTable: '',
    refColumn: '',
    refConditionColumn: '',
    refConditionValue: '',
    refConditionQueryMethod: '',
    customize: '',
    authTable: '',
    level: 0,
    isNullable: '',
    isReadonly: '',
    isReadonlyInput: '',
    defaultValue: '',
    unique: '去',
    loginUser: '',
    formatValidation: '',
    sort: '',
    sessionRead: '',
    sessionColumn: '',
    compare: '',
    alipayColumn: '',
    tplist: '',
    crossCal: '无',
    examName: '',
    minLength: '',
    maxLength: '',
    faceMatch: '',
    hiden: '',
    encryption: '',
    courseFlag: '',
    columnDefault: '',
    remind: '',
    remindStart: '',
    remindEnd: '',
    remindInput: '',
    colLength: '32',
    detail_type: 'DetailTitle',
    detail_order: 2,
    form_type: 'YyText',  
    form_hidden: false,    
    options: [],
  },
]

export let searchColumns = [
]

export let headerButtons = [
  {
    title: '新增',   
    name: '新增',
    key: 'add',
    iconName: 'Plus',
    className: 'action-add',
  },
]
export let tableButtons = []

/**
 * @description 课程表
 * titleNames: 标题
 * subTitleNames: 副标题
 * searchNames: 用权。显示的时候，根据这个条件过滤
 */ 
export let timeTable = {
  titleNames: [],
  subTitleNames: [],
  searchNames: []
}

export let detailConfig = {
  hasIsanon: false,
  titleName: '',
  titleNames: [],
  titleHeads: [],
  imgName: '',
  imgNames: [],
  pubPeopleName: '',
  sortName: '',
  sortOrder: 'desc',
  videoNames: [],
  inteltypeName: '',
  merchantName: '',
  payBtns: [
  ],
  hasBuyInput: false,
  actionBtns: [
    {
      title: '修改',    
      name: '修改',
      key: 'edit',
      iconName: 'Edit',
    },
    {
      title: '删除',
      name: '删除',
      key: 'remove',
      iconName: 'Delete',
    },
  ],
  tabs: [
  ],
  sortList: [
  ]
}