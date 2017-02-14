# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)
User.create!(name:  "SFlab",
             email: "sflab@alpha.sf.cs.it-chiba.ac.jp",
             password:              "enjoysf",
             password_confirmation: "enjoysf",
             admin: true)

User.create!(name:  "Momoko Karasawa",
             email: "gretel.leez@gmail.com",
             password:              "grqt58yj",
             password_confirmation: "grqt58yj",
             admin: false)