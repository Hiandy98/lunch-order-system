# Store Entity 實作細節

## 1. 模型內容
參考`user.entity.User`進行實作`Store`之充血模型，並使用靜態工廠方法模式取代傳統購毽子。
模型採用充血模型 (將業務邏輯（行為）和數據（狀態）封裝在同一個領域對象中，讓對象具備完整的自我管理能力)，
可以參考`user.entity.User`中的使用。

## 2. 欄位
| 資料型態     | 名稱     | 做用               |
|--------------|----------|--------------------|
| int          | id       | 主鍵、唯一辨識碼   |
| String       | name     | 紀錄商家名稱       |
| StoreContent | content  | 菜單與各種商家資料 |
| Date         | createAt | 創建時間           |
| Date         | updateAt | 更新時間           |

