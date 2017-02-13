module UsersHelper
	def gravatar_for(user)
    gravatar_id = Digest::MD5::hexdigest(user.email.downcase)
    gravatar_url = "https://secure.gravatar.com/avatar/#{gravatar_id}"
    image_tag(gravatar_url, alt: user.name, class: "gravatar")
  end

  def image_for(user)
    if user.image
      image_tag "/#{user.image}"
    else
      image_tag "neko.png"
    end
  end
end
