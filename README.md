# 🏨 JavaSE Hotel Project

## 👥 Team Members
1. علیرضا نوراحمدی (Project Manager)
2. شایان امینائی
3. امیرسبحان قندی‌زاده

---

## 📆 Timeline
- پایان پروژه: ۱۵ مهر

---

## 📂 Project Structure

---

## 📐 UML Final
![Hotel UML](docs/hotel-uml.png)

---

## 🚀 Roadmap (Step by Step)

### ✅ فاز اول (۲۹ تا ۳۱ شهریور) — Entity & Enum
- طراحی کامل کلاس‌های انتزاعی و اصلی بر اساس UML نهایی.
- ایجاد Enumهای مورد نیاز.

تقسیم وظایف:
- علیرضا:
    - AbstractHotel
    - Branch
    - Person
    - آماده‌سازی ساختار پروژه و گیتهاب
- شایان:
    - Room
    - Reserve
    - Payment
    - Enums: RoomType, PaymentStatus, ReservationStatus, RoomStatus
- امیرسبحان:
    - Employee
    - Manager
    - Task
    - Enums: Role, TaskStatus
    - Guest
    - Property, AssignProperty

---

### ✅ فاز دوم (۱ تا ۵ مهر) — Query Layer
- نوشتن Queryهای لازم برای Entityها (CRUD پایه).

تقسیم وظایف:
- علیرضا: Branch, Hotel, Property, Person
- شایان: Room, Reserve, Guest
- امیرسبحان: Employee, Task, Manager, Payment

---

### ✅ فاز سوم (۶ تا ۷ مهر) — Mapper Layer
- ایجاد Mapperها (تبدیل Entity ↔ DTO یا نمایش بهتر).

تقسیم وظایف:
- علیرضا: HotelMapper, BranchMapper, PropertyMapper, PersonMapper
- شایان: RoomMapper, ReservationMapper, GuestMapper
- امیرسبحان: EmployeeMapper, TaskMapper, ManagerMapper, PaymentMapper

---

### ✅ فاز چهارم (۸ تا ۹ مهر) — Service Layer
- پیاده‌سازی لایه Service برای منطق برنامه.
- service interface :شایان  

تقسیم وظایف:
- علیرضا: HotelService, BranchService, PropertyService, PersonService
- شایان: RoomService, ReservationService, GuestService
- امیرسبحان: EmployeeService, TaskService, ManagerService, PaymentService

---

### ✅ فاز پنجم (۱۰ مهر) — Repository Layer
- ایجاد Repository برای مدیریت CRUD.

تقسیم وظایف:
- علیرضا: Repository برای Branch, Hotel, Property, Person
- شایان: Repository برای Room, Reserve, Guest
- امیرسبحان: Repository برای Employee, Task, Manager, Payment

---

### ✅ فاز ششم (۱۱ مهر) — Controller Layer
- ایجاد Controllerها برای اتصال Serviceها به لایه UI.

تقسیم وظایف:
- علیرضا: HotelController, BranchController, PropertyController, PersonController
- شایان: RoomController, ReservationController, GuestController
- امیرسبحان: EmployeeController, TaskController, ManagerController, PaymentController

---

### ✅ فاز هفتم (۱۲ تا ۱۳ مهر) — FXML Layer
- طراحی صفحات UI با JavaFX (FXML).

تقسیم وظایف:
- علیرضا: HotelUI, BranchUI, PropertyUI, PersonUI
- شایان: RoomUI, ReservationUI, GuestUI
- امیرسبحان: EmployeeUI, TaskUI, ManagerUI, PaymentUI

---

### ✅ فاز هشتم (۱۴ مهر) — Test Layer
- تست نهایی برای کلاس‌ها و سناریوهای اصلی.

تقسیم وظایف:
- علیرضا: تست Hotel, Branch, Property, Person
- شایان: تست Room, Reservation, Guest
- امیرسبحان: تست Employee, Task, Manager, Payment

---

### ✅ فاز نهم (۱۵ مهر) — Integration & Finalization
- تست نهایی کل سیستم.
- اجرای سناریوهای تست (رزرو اتاق، پرداخت، تخصیص کارمند، مدیریت شعبه و …).
- ادغام کدها در main branch.
- مستندسازی نهایی و آماده‌سازی برای تحویل.

---

## 🎯 Goal
در پایان پروژه، یک سیستم مدیریت هتل به صورت JavaSE (JDK 1.8) با ساختار لایه‌ای (Entity, Query, Mapper, Service, Repository, Controller, FXML, Test) آماده خواهد شد که تمام نیازهای تعریف‌شده در UML نهایی را پوشش می‌دهد.
