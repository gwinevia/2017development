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

User.create!(name:  "Sample User",
             email: "sample@gmail.com",
             password:              "password",
             password_confirmation: "password",
             admin: false)

User.create!(name:  "Sample User",
             email: "sample2@gmail.com",
             password:              "password2",
             password_confirmation: "password2",
             admin: false)

# users = User.order(:created_at).take(6)
# 50.times do
#   content = Faker::Lorem.sentence(5)
#   users.each { |user| user.microposts.create!(content: content) }
# end