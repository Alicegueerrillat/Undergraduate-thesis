
export let table = {
  tableName: 'chat',
  comments: 'AI助手',
}

export let columns = [
  {
    columnName: 'uname',
    comments: '用户名',
    form_type: 'YyText',
  },  
  {
    columnName: 'ask',
    comments: '消息',
    form_type: 'YyText',
  },  
  {
    columnName: 'isreply',
    comments: '是否回复',
    form_type: 'YySingleSelect',
    table_type: 'TableTag',
    options: [
      {
        value: 1,
        label: '未回',
        type: 'warning',
      },
      {
        value: 0,
        label: '已回',
        type: 'success',
      },
    ]
  },  
  {
    columnName: 'isread',
    comments: '是否已读',
    form_type: 'YySingleSelect',
    table_type: 'TableTag',
    options: [
      {
        value: 0,
        label: '未读',
        type: 'warning',
      },
      {
        value: 1,
        label: '已读',
        type: 'success',
      },
    ]
  },  
]

export let searchColumns = [
 {
    columnName: 'isreply',
    comments: '是否回复',
    form_type: 'YySingleSelect',
    table_type: 'TableTag',
    options: [
      {
        value: 1,
        label: '未回',
        type: 'warning',
      },
      {
        value: 0,
        label: '已回',
        type: 'success',
      },
    ]
  },  
  {
    columnName: 'isread',
    comments: '是否已读',
    form_type: 'YySingleSelect',
    table_type: 'TableTag',
    options: [
      {
        value: 0,
        label: '未读',
        type: 'warning',
      },
      {
        value: 1,
        label: '已读',
        type: 'success',
      },
    ]
  },  
]

export let headerButtons = [
  {
    title: '删除',
    name: '删除',
    key: 'removes',
    iconName: 'Delete',
    className: 'action-removes',
  },
]

export let tableButtons = [
  {
    title: '回复',
    name: '回复',
    key: 'chatRepeat',
    iconName: 'ChatLineSquare',
    isPublic: true,
  },
  {
    title: '删除',
    name: '删除',
    key: 'remove',
    className: 'action-delete',
    iconName: 'Delete',
  },
]