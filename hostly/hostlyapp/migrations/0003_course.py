# Generated by Django 4.1.7 on 2024-10-09 09:08

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('hostlyapp', '0002_department'),
    ]

    operations = [
        migrations.CreateModel(
            name='Course',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('course_name', models.CharField(max_length=200)),
                ('type', models.CharField(max_length=200)),
                ('DEPARTMENT', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='hostlyapp.department')),
            ],
        ),
    ]