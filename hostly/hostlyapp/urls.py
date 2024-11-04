"""hostly URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from.import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('',views.index),
    path('login',views.login),
    path('admin_home',views.admin_home),
    path('admin_changepass',views.admin_changepass),
    path('admin_department',views.admin_department),
    path('admin_add_department',views.admin_add_department),
    path('admin_delete_dep/<id>',views.admin_delete_dep),
    path('admin_edit_dep/<id>',views.admin_edit_dep),
    path('admin_course',views.admin_course),
    path('admin_add_course',views.admin_add_course),
    path('admin_delete_course/<id>',views.admin_delete_course),
    path('admin_edit_course/<id>',views.admin_edit_course),
    path('admin_student',views.admin_student),
    path('admin_accept_student/<id>',views.admin_accept_student),
    path('admin_reject_student/<id>',views.admin_reject_student),
    # path('admin_add_student',views.admin_add_student),
    # path('admin_delete_student/<id>',views.admin_delete_student),
    # path('admin_edit_student/<id>',views.admin_edit_student),
    path('admin_warden',views.admin_warden),
    path('admin_add_warden',views.admin_add_warden),
    path('admin_delete_warden/<id>',views.admin_delete_warden),
    path('admin_edit_warden/<id>',views.admin_edit_warden),
    # path('admin_attendance',views.admin_attendance),
    path('admin_send_notification',views.admin_send_notification),
    path('admin_add_notification',views.admin_add_notification),
    path('admin_delete_notification/<id>',views.admin_delete_notification),
    path('admin_view_complaint',views.admin_view_complaint),
    path('admin_reply_complaint/<id>',views.admin_reply_complaint),
    path('admin_view_payments',views.admin_view_payments),
    path('admin_view_roomallocations',views.admin_view_roomallocations),
    path('admin_view_activities',views.admin_view_activities),
    path('manage_room',views.admin_manage_rooms),









    # -----------------------------------andriod-----------------------
    path('login_and',views.login_and),
    path('student_registration',views.student_registration),
    path('view_course',views.view_course),
    path('view_rooms',views.view_rooms),
    path('view_students',views.view_students),
    path('warden_allocate_room',views.warden_allocate_room),
    path('student_view_allocated_room',views.student_view_allocated_room),
    path('sendmaintenance_request',views.sendmaintenance_request),
    path('search_students',views.search_students),
    path('std_present',views.std_present),
    path('std_abscent',views.std_abscent),
    path('view_notification',views.view_notification),
    path('student_view_attendance',views.student_view_attendance),
    path('studentpayment',views.studentpayment),
    path('view_reply',views.view_reply),
    path('psend_complaints',views.psend_complaints),
    path('checkin',views.checkin),

    path('view_Checkin',views.view_Checkin),
    path('send_Addontime',views.send_Addontime),
    path('approve_status',views.approve_status),
    path('reject_status',views.reject_status),
    path('viewalladdontimes',views.viewalladdontimes),
    path('studentviewaddontime',views.studentviewaddontime),

    path('wardern_view_students',views.wardern_view_students),
    path("view_sendmaintenance_request",views.view_sendmaintenance_request),
    path("wardenviewmaintancerequest",views.wardenviewmaintancerequest),
    path("acceptmaintaincerequest",views.acceptmaintaincerequest),




















]
