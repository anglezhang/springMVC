function DateFmt(value,fmt)
	{
		console.debug("value=" + value);
	}
	$(function() {
		var editIndex = -1;
		var count = '${fn:length(guestList)}';
		//var countries = 'United S,Germany,UK,Japan,Italy,Greece'.split(',');
		//var data = eval('(' + '${object}' + ')');
		var data = $.parseJSON('${object}');
		var childData = eval('(' + '${childData}' + ')');
		var toFilter;
		var cvData          = new wijmo.collections.CollectionView(data),
			/* groupingNames   = new Array("bulidId","floorNo","currStat"),
			groupingFieldNameList = document.getElementById('groupingFieldNameList'),
		    btnFirstPage    = document.getElementById('btnMoveToFirstPage'),
		    btnPreviousPage = document.getElementById('btnMoveToPreviousPage'),
		    btnNextPage     = document.getElementById('btnMoveToNextPage'),
		    btnLastPage     = document.getElementById('btnMoveToLastPage'),
		    btnCurrentPage  = document.getElementById('btnCurrentPage'),
		    filteringInput  = document.getElementById('filteringInput'), */
		    gsGrid          = new wijmo.grid.FlexGrid('#gsGrid'),
		    editedArray = new wijmo.collections.ObservableArray(),
		    addArray   = new wijmo.collections.ObservableArray();
		// initialize grid
		gsGrid.initialize({
			isReadOnly: false,
			selectionMode : wijmo.grid.SelectionMode.Row,
			allowMerging : wijmo.grid.AllowMerging.All,
			allowSorting : true,
			autoGenerateColumns : false,
			allowAddNew: true,
  			allowDelete: true,
  			columns: [
  					{ header: '选择', binding:'checkId',width:150},
                    { header: '房号', binding: 'roomId' , width:150}, 
                    { header: '房类', binding: 'roomType' , width:150,allowMerging:false},
                    { header: '楼号', binding: 'buildId' , width:150,allowMerging:false},
                    { header: '楼层', binding: 'floorNo' , width:150,allowMerging:true},
                    { header: '客房特征', binding: 'roomCharacter' , width:150},
                    { header: '当前房态', binding: 'currStat' , width:150},
                    { header: '修改前房态', binding: 'modiStat' , width:150},
                    { header: '修改房态时间', binding: 'modiTime' ,width:150,format:DateFmt},
                    { header: '修改房态操作员', binding: 'modiOperid' , width:150},
                    { header: '状态', binding: 'status' , width:150},
                    { header: '描述', binding: 'description' },
                    { header: '操作', binding: "buttons", name: "buttons" }
                ],
                itemsSource : cvData,
		});
		gsGrid.itemFormatter = function(panel, r, c, cell){
			 if (panel.cellType == wijmo.grid.CellType.Cell) {
                var col = panel.columns[c],
                    html = cell.innerHTML;
                if (r == editIndex) {
                	
                }else{
                	switch (col.name) {
                        case 'buttons':
                            cell.style.padding = '3px';
                            html = '<div>' +
                                   '&nbsp;&nbsp;' +
                                   '<button class="btn btn-default btn-sm" onclick="editRow(' + r + ')">' +
                                       '<span class="glyphicon glyphicon-pencil"></span> Edit' +
                                   '</button>' +
                               '</div>';
                            break;
                    }
                }
                
                if (html != cell.innerHTML) {
                    cell.innerHTML = html;
                    /* if (col.name == "date") {
                        inputDate = new wijmo.input.InputDate("#theDate");
                        inputDate.value = new Date(panel.getCellData(r, c, true));
                    } */
                    cell.style.padding = '3px';
                }
             }
		};
		
		var col = gsGrid.columns.getColumn('roomType');
		col.dataMap = new wijmo.grid.DataMap(childData, 'id', 'typeName');
		
		var roomId =  gsGrid.columns.getColumn("roomId");
		roomId.dataType = wijmo.DataType.Boolean;
		
		var checkId = gsGrid.columns.getColumn("checkId");
		checkId.dataType = wijmo.DataType.Boolean;

		gsGrid.itemFormatter = function(panel, r, c, cell)
		{
			if(panel.cellType == 3)
			{
				var col = panel.columns[c];
				console.debug("name = " + panel );
				if (col.name == 'checkId') 
				{
			      cell.innerHTML = 'is new ';
			    }
			}
		}
		// create editors for columns
       //createEditor(gsGrid.columns.getColumn('roomId'));
       //createEditor(gsGrid.columns.getColumn('modiTime'));
       //createEditor(gsGrid.columns.getColumn('roomType'));
       
        // track the collection changes so that updating the grid.
		cvData.trackChanges = true;
		//cvData.pageSize = 10;
		
		///
		// Add the processes for buttons' click
		document.getElementById('btnEdit').addEventListener('click', function () {
		  
		  editedArray.clear();
		  //cvData.beginUpdate​();
		  cvData.commitEdit();
		  //console.info("aa:"+cvData.currentEditItem);
		  //console.info(cvData.itemsEdited);
		  editedArray.push(cvData.itemsEdited);
		  //addArray.push(cvData.itemsAdded);
		 
		  console.info(editedArray);
		 /*  console.info("-----start-----");
		  console.info(editedArray[0]);
		  console.info("-----end-----"); */
		  var temp = editedArray[0];
		  console.info(temp.length);
		  /* console.info(temp[0].roomId);
		  console.info(temp[1].roomId);
		  console.info(temp[2].roomId); */
		  //console.info(cvData.itemsEdited);
		  console.info("----------");
		  //console.info(addArray.r);
		  //cvEditing.editItem(cvData.currentItem);
		
		  // update the content in the dialog with the current edited item.
		  //updateDialog(cvData.currentEditItem, true);
		});
		
		
		
		///
		/* document.getElementById('pagingInput').value = cvData.pageSize;
		updateNaviagteButtons();
		// update the collectionview's pagesize according to the user's input.
		document.getElementById('pagingInput').addEventListener('blur', function () {
		  var pagesize = this.value;
		
		  if (!pagesize) {
		    pagesize = 0;
		  } else {
		    pagesize = wijmo.Globalize.parseInt(pagesize);
		  }
		
		  cvData.pageSize = pagesize;
		  updateNaviagteButtons();
		}); */
		
		// update the navigation buttons' status
		/* function updateNaviagteButtons() {
		  if (cvData.pageSize <= 0) {
		    document.getElementById('naviagtionPage').style.display = 'none';
		    return;
		  }
		
		  document.getElementById('naviagtionPage').style.display = 'block';
		
		  if (cvData.pageIndex === 0) {
		    btnFirstPage.setAttribute('disabled', 'disabled');
		    btnPreviousPage.setAttribute('disabled', 'disabled');
		    btnNextPage.removeAttribute('disabled');
		    btnLastPage.removeAttribute('disabled');
		  } else if (cvData.pageIndex === (cvData.pageCount - 1)) {
		    btnFirstPage.removeAttribute('disabled');
		    btnPreviousPage.removeAttribute('disabled');
		    btnLastPage.setAttribute('disabled', 'disabled');
		    btnNextPage.setAttribute('disabled', 'disabled');
		  } else {
		    btnFirstPage.removeAttribute('disabled');
		    btnPreviousPage.removeAttribute('disabled');
		    btnNextPage.removeAttribute('disabled');
		    btnLastPage.removeAttribute('disabled');
		  }
		
		  btnCurrentPage.innerHTML = (cvData.pageIndex + 1) + ' / ' + cvData.pageCount;
		} */
		
		//排序 start
		/* var sd = new wijmo.collections.SortDescription('buildId', true);
		 var sd1 = new wijmo.collections.SortDescription('roomType', true);
		
		cvData.sortDescriptions.push(sd);
		cvData.sortDescriptions.push(sd1); */
		/*  gsGrid.sortingColumn.addHandler(function(s,e){
		 	console.info("-------------");
		 	console.info(s);
		 	console.info(e);
		 }); */
		//排序 end
		/**-------------------分组--------------------*/
		// initialize the list and listen to the list's change.
		/* groupingFieldNameList.innerHTML += '<option value="" selected="selected">Please choose the field you want to group by...</option>';
		for (var i = 0; i < groupingNames.length; i++) {
		  groupingFieldNameList.innerHTML += '<option value="' + groupingNames[i] + '">' + groupingNames[i] + '</option>';
		}
		groupingFieldNameList.addEventListener('change', groupGrid); */
		
		/* function groupGrid() {
		  var gd,
		      fieldName = groupingFieldNameList.value;
		
		  gd = cvData.groupDescriptions;
		
		  if (!fieldName) {
		    // clear all the group settings.
		    gd.splice(0, gd.length);
		    return;
		  }
		
		  if (findGroup(fieldName) >= 0) {
		    return;
		  }
		
		  if (fieldName === 'amount') {
		    // when grouping by amount, use ranges instead of specific values
		   gd.push(new wijmo.collections.PropertyGroupDescription(fieldName, function (item, propName) {
		      var value = item[propName]; // amount
		      if (value > 1000) return 'Large Amounts';
		      if (value > 100) return 'Medium Amounts';
		      if (value > 0) return 'Small Amounts';
		      return 'Negative Amounts';
		    }));
		  }
		  else {
		    // group by specific property values
		    gd.push(new wijmo.collections.PropertyGroupDescription(fieldName));
		  }
		} */
		
		// check whether the group with the specified property name already exists.
		/* function findGroup(propName) {
		  var gd = cvData.groupDescriptions;
		  for (var i = 0; i < gd.length; i++) {
		    if (gd[i].propertyName === propName) {
		      return i;
		    }
		  }
		
		  return -1;
		} */
			     
		
		
		/**----------------过滤filter------------------*/
		/* filteringInput.addEventListener("input", filterGrid);
		// define the filter function for the collection view.
		function filterFunction(item) {
			var filter = filteringInput.value.toLowerCase();
			if (!filter) {
				return true;
			}

			return item.description.toLowerCase().indexOf(filter) > -1;
		}

		function filterGrid() {
			if (toFilter) {
				clearTimeout(toFilter);
			}
			toFilter = setTimeout(function() {
				toFilter = null;
				if (cvData.filter === filterFunction) {
					cvData.refresh();
				} else {
					cvData.filter = filterFunction;
				}
			}, 500);
		} */
		
		/*****Tracking changes******/
	});
	
	
	/* function createEditor(editColumn){
		var grid = editColumn.grid;
		 grid.formatItem.addHandler(function (s, e) {
                var editRange = grid.editRange,
                    column = e.panel.columns[e.col];
                // check whether this is an editing cell of the wanted column
                if (!(e.panel.cellType === wijmo.grid.CellType.Cell && column === editColumn && editRange && editRange.row === e.row && editRange.col === e.col)) {
                    return;
                }

                // hide standard editor (don't remove!)
                if (e.cell.firstChild) {
                    e.cell.firstChild.style.display = 'none';
                }

                // add custom editor
                var editorRoot = document.createElement('div');
                var input;
                if (column.dataType === wijmo.DataType.Date) {
                    input = new wijmo.input.InputDate(editorRoot);
                } else if (column.binding == "roomType") {
                    // as an ICollectionView
                    //var countries = ['已修改','未修改'];
                    console.info("~~~~~~~~~~~~~");
                    
                    var childData = $.parseJSON('${childData}');
                    var idarray = new Array();
                    var showArray = new Array();
                    idarray.splice(0,idarray.length);
                    showArray.splice(0,showArray.length);
                    for(var i=0;i<childData.length;i++){
                      
                      idarray.push(childData[i].id);
                      showArray.push(childData[i].typeName);
                    }
                    console.info(idarray);
                    var combobox = new wijmo.input.ComboBox(editorRoot, {
                        itemsSource: childData,
                    });
                    console.info(childData.typeName);
                    combobox.selectedItem = idarray;
                    combobox.selectedValue = showArray;
                    combobox.itemFormatter = function(index, content){
                    	return showArray;
                    };
                    input = combobox;
                }
                else {
                    input = new wijmo.input.InputNumber(editorRoot);
                    input.step = 1;
                    input.format = editColumn.format;
                }

                e.cell.appendChild(editorRoot);
                input.value = grid.getCellData(e.row, e.col, false);

                // cellEditEnding that updates cell with user's input
                var editEndingEH = function (s, args) {
                    grid.cellEditEnding.removeHandler(editEndingEH);
                    if (!args.cancel) {
                        args.cancel = true;
                        if (args.col == 4) {
                            cellValue = input.selectedItem;
                            grid.setCellData(e.row, e.col, cellValue);
                        }
                        else {
                            grid.setCellData(e.row, e.col, input.value);
                        }
                    }
                };

                // subscribe the handler to the cellEditEnding event
                grid.cellEditEnding.addHandler(editEndingEH);
            });
	} */
	
	 function editRow(row) {
            //editIndex = row;
           // flex.invalidate();
           alert(row);
        }